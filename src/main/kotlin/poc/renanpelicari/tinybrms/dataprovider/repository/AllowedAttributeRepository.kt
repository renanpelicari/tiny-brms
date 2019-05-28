package poc.renanpelicari.tinybrms.dataprovider.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import poc.renanpelicari.tinybrms.dataprovider.domain.AllowedAttribute

@Repository
interface AllowedAttributeRepository : PagingAndSortingRepository<AllowedAttribute, String>
