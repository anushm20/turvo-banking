/**
 * Test Class for Branch Service
 */
package com.turvo.banking.branch.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.turvo.banking.AbcApplication;
import com.turvo.banking.branch.counter.entities.BranchCounterMapping;
import com.turvo.banking.branch.counter.services.BranchCounterMappingServices;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class BranchServiceTest {
	
	@Autowired
	BranchCounterMappingServices branchCounterMappingServices;

	@Test
	public void createBranchService() {
		BranchCounterMapping mapping = new BranchCounterMapping();
		mapping.setServiceId(1L);
		mapping.setServiceCounters(Arrays.asList(new Long[] {1L,2L}));
		branchCounterMappingServices.createServiceToServiceCounterMapping(mapping);
		List<Long> counters = branchCounterMappingServices.getServiceCountersForService(1L);
		assertEquals(counters, mapping.getServiceCounters());
	}
}
