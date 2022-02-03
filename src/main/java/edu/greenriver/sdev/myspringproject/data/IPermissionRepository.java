package edu.greenriver.sdev.myspringproject.data;

import edu.greenriver.sdev.myspringproject.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for the Permission model that creates a repository with CRUD access to Permission database records
 * @author Ryan Rivera
 * @version 1.0
 */
@Repository
public interface IPermissionRepository extends JpaRepository<Permissions, Integer>
{

}