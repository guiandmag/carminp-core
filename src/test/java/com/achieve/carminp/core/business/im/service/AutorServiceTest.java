package com.achieve.carminp.core.business.im.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * @author guilherme.magalhaes
 *
 */
@RunWith(JUnit4.class)
public class AutorServiceTest {
	
	@Inject
	private IAutorService autorService;

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#save(com.achieve.carminp.core.model.im.entidade.AutorEntidade)}.
	 */
	@Test
	public void testSaveAutorEntidade() {
		AutorEntidade autor = new AutorEntidade();
		autor.setNome("Guilherme Magalhaes");
		
		autorService.save(autor);
		
		assertNotNull(autor.getId());
	}
	

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#getById(java.lang.Object)}.
	 */
	@Test
	public void testGetByIdObject() {
		IEntity<Long> autor = autorService.getById(1);
		
		assertNotNull(autor);
		assertSame(autor.getId(), 1L);
	}

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<AutorEntidade> autores = autorService.findAll();
		
		assertNotNull(autores);
	}

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#findByFields(java.util.Map, java.lang.Boolean, int, java.lang.String)}.
	 */
	@Test
	public void testFindByFieldsMapOfStringObjectBooleanIntString() {
	}

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#getMostAuthorsWithPhrases()}.
	 */
	@Test
	public void testGetMostAuthorsWithPhrases() {
	}

	/**
	 * Test method for {@link com.achieve.carminp.core.business.im.service.AutorService#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		IEntity<Long> autor = autorService.getById(1);
		autorService.delete(autor.getId());
		
		assertNull(autor);
	}
	
}