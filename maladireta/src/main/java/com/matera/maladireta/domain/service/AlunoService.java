package com.matera.maladireta.domain.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.matera.maladireta.domain.Aluno;
import com.matera.maladireta.domain.Nota;
import com.matera.maladireta.domain.exception.InvalidAlunos;
import com.matera.maladireta.domain.exception.InvalidNota;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@RefreshScope
public class AlunoService {
	
	private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${aluno.url}")
	private String url;

	private final Cache<String, List<Aluno>> cacheAlunos = CacheBuilder.newBuilder().maximumSize(100)
			.expireAfterWrite(24L, TimeUnit.HOURS).build();
	
	private final Cache<String, Nota> cacheNotas = CacheBuilder.newBuilder().maximumSize(100)
			.expireAfterWrite(24L, TimeUnit.HOURS).build();

	@HystrixCommand(fallbackMethod = "getAllAlunosInCache")
	public List<Aluno> getAllAlunos() {
		log.info("[REQUEST-ALUNOS-INFO] Request event info ");
		ResponseEntity<Aluno[]> response = this.restTemplate.getForEntity(this.url,
				Aluno[].class);
		List<Aluno> alunos = Arrays.asList(response.getBody());
		
		cacheAlunos.put("all", alunos);
		return alunos;
	}

	public List<Aluno> getAllAlunosInCache() {
		List<Aluno> alunos = cacheAlunos.getIfPresent("all");
		if (Objects.isNull(alunos)) {
			log.error(String.format("[REQUEST-ALUNOS-INFO] CACHE - Error on retrieve Alunos information"));
			throw new InvalidAlunos();
		}
		return alunos;
	}
	
	
	@HystrixCommand(fallbackMethod = "getNotaByCpfInCache")
	public Nota getNotaByCpf(String cpf) {
		log.info("[REQUEST-NOTA-INFO] Request event info ");
		ResponseEntity<Nota> response = this.restTemplate.getForEntity(this.url + "/" + cpf + "/notas",
				Nota.class);
		final Nota nota = response.getBody();
		cacheNotas.put(cpf, nota);
		return nota;
	}
	
	/**
	 * Retrieve participant from cache
	 * 
	 * @param participantId
	 * @return
	 */
	public Nota getNotaByCpfInCache(String cpf) {
		Nota cachedNota = cacheNotas.getIfPresent(cpf);
		if (Objects.isNull(cachedNota)) {
			log.error(String.format("[REQUEST-NOTA-INFO] CACHE - Error on retrieve notas %s information",
					cpf));
			throw new InvalidNota(cpf);
		}
		return cachedNota;
	}
}

