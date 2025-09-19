// 代码生成时间: 2025-09-19 19:07:06
package com.example.security

import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AbstractAuthenticationEvent
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
import org.springframework.security.web.authentication.WebAuthenticationDetails

/**
 * Security Audit Log Service
 * This service is responsible for logging security events such as authentication attempts.
 */
@Slf4j
@Transactional
class SecurityAuditLogService {

    // Method to log successful authentication events
    void logAuthenticationSuccess(AbstractAuthenticationEvent event) {
        try {
            String username = event.authentication.name
            WebAuthenticationDetails authDetails = event.authentication.details as WebAuthenticationDetails
            log.info "Successful login attempt for user: ${username}, IP: ${authDetails.remoteAddress}"

            // Additional logic to save the log to the database can be added here
        } catch (Exception e) {
            log.error "Error logging authentication success: ${e.message}"
        }
    }

    // Method to log failed authentication attempts
    void logAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        try {
            String username = event.authentication.name
            WebAuthenticationDetails authDetails = event.authentication.details as WebAuthenticationDetails
            log.warn "Failed login attempt for user: ${username}, IP: ${authDetails.remoteAddress}"

            // Additional logic to save the log to the database can be added here
        } catch (Exception e) {
            log.error "Error logging authentication failure: ${e.message}"
        }
    }

    // Method to handle security events
    void handleSecurityEvent(Object event) {
        if (event instanceof AbstractAuthenticationEvent) {
            logAuthenticationSuccess(event)
        } else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            logAuthenticationFailure(event)
        }
    }
}

/**
 * Security Audit Log Listener
 * This listener is responsible for listening to security events and delegating
 * them to the SecurityAuditLogService for logging.
 */
@Slf4j
class SecurityAuditLogListener implements ApplicationListener<AbstractAuthenticationEvent> {

    SecurityAuditLogService securityAuditLogService

    @Override
    void onApplicationEvent(AbstractAuthenticationEvent event) {
        try {
            if (event instanceof AuthenticationFailureBadCredentialsEvent) {
                securityAuditLogService.logAuthenticationFailure(event)
            } else {
                securityAuditLogService.logAuthenticationSuccess(event)
            }
        } catch (Exception e) {
            log.error "Error handling security event: ${e.message}"
        }
    }
}
