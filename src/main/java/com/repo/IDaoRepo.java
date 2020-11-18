/**
 * 
 */
package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maxime
 *
 */
public interface IDaoRepo<E> extends JpaRepository<E, Long>{

}
