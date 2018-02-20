/**
 * Test Class for Branch Service
 */
package com.turvo.banking.branch.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class BranchServiceTest {
	
	@Test
	public void createBranchService() {
		/*BranchCounterMapping mapping = new BranchCounterMapping();
		mapping.setServiceId(1L);
		mapping.setServiceCounters(Arrays.asList(new Long[] {1L,2L}));
		branchCounterMappingServices.createServiceToServiceCounterMapping(mapping);
		List<Long> counters = branchCounterMappingServices.getServiceCountersForService(1L);
		assertEquals(counters, mapping.getServiceCounters());*/
	}
}
