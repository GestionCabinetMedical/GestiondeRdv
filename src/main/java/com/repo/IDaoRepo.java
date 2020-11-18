/**
 * 
 */
package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Repository générique responsable de la communication avec la base
 * de données. Elle implémente JpaRepository.
 * 
 * @author Maxime Rembert
 *
 */
public interface IDaoRepo<E> extends JpaRepository<E, Long> {

}
