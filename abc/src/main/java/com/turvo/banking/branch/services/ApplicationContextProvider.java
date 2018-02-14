/**
 * 
 */
package com.turvo.banking.branch.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author anushm
 *
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	
	private static ApplicationContext context;
    
    public static ApplicationContext getApplicationContext() {
        return context;
    }

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		context = ac;
	}

}
