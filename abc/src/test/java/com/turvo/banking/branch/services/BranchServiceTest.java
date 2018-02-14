/**
 * 
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
import com.turvo.banking.branch.entities.ServiceToServiceCounterMapping;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
public class BranchServiceTest {
	
	@Autowired
	BranchServices branchServices;

	@Test
	public void createBranchService() {
		ServiceToServiceCounterMapping mapping = new ServiceToServiceCounterMapping();
		mapping.setServiceId(1L);
		mapping.setServiceCounters(Arrays.asList(new Long[] {1L,2L}));
		branchServices.createServiceToServiceCounterMapping(mapping);
		List<Long> counters = branchServices.getServiceCountersForService(1L);
		assertEquals(counters, mapping.getServiceCounters());
	}
}
