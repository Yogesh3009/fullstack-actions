/**
 * 
 */
package com.fullstack.app.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author swapnil
 *
 */
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityScopeRes {
	private boolean status;
	private String userName;
}
