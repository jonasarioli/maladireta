package com.matera.maladireta.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.maladireta.domain.Aluno;
import com.matera.maladireta.domain.Disciplina;
import com.matera.maladireta.domain.MalaDireta;
import com.matera.maladireta.domain.Nota;

@Service
public class MalaDiretaService {

	private final AlunoService alunoService;

	@Autowired
	public MalaDiretaService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	public Integer process(List<MalaDireta> input) {

		List<Aluno> listaDeAlunos = alunoService.getAllAlunos();
		List<MalaDireta> emailsProcessados = new ArrayList<>();

		if (!input.isEmpty()) {
			Predicate<Disciplina> menorDoQue7 = (n) -> n.getNota() < 7.00;

			Set<String> acceptableNames = input.stream().map(MalaDireta::getNome).collect(Collectors.toSet());

			// filtra os alunos que devem ser enviada a mala direta
			List<Aluno> alunos = listaDeAlunos.stream().filter(c -> acceptableNames.contains(c.getNome()))
					.collect(Collectors.toList());

			// verificar para cada aluno que deve ser enviado, se possui nota menor do que 7.0
			for (Aluno aluno : alunos) {
				Nota nota = alunoService.getNotaByCpf(aluno.getDocumento());
				if (nota != null && !nota.getNotas().isEmpty()) {
					Optional<Disciplina> result = nota.getNotas().stream().filter(menorDoQue7).findFirst();
					if (result.isPresent()) {
						MalaDireta maladireta = new MalaDireta(aluno);
						emailsProcessados.add(maladireta);
					}
				}
			}
		}

		return emailsProcessados.size();
	}
}
