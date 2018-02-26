/**
 * Common Test Class to place all configuration for Junit tests
 */
package com.turvo.banking;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author anushm
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AbcApplication.class})
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class AbstractCommonTest {

}
