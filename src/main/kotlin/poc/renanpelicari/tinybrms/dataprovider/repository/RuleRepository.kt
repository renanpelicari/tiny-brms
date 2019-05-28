package poc.renanpelicari.tinybrms.dataprovider.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import poc.renanpelicari.tinybrms.dataprovider.domain.Rule

@Repository
interface RuleRepository : PagingAndSortingRepository<Rule, String> {

    fun findRulesByMatchersAttributeNameIn(attributeNames: List<String>): Set<Rule>
}
