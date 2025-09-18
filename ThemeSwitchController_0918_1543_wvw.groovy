// 代码生成时间: 2025-09-18 15:43:32
package com.yourapp.controller

import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping('/api/theme')
@Transactional
class ThemeSwitchController {

    // 依赖注入LinkGenerator来生成URLs
    private final LinkGenerator linkGenerator

    // 构造函数注入
    ThemeSwitchController(LinkGenerator linkGenerator) {
        this.linkGenerator = linkGenerator
    }

    /**
     * Handle GET request to switch theme.
     * This endpoint will change the theme for the current session.
     * It returns the new theme and a URL to the original page to maintain user navigation.
     */
    @GetMapping('/switch')
    ResponseEntity switchTheme(@RequestParam String theme, HttpSession session) {
        try {
            // Validate theme parameter
            if (!['light', 'dark'].contains(theme)) {
                return ResponseEntity.badRequest().body(['error': "Invalid theme '${theme}'. Available themes are 'light' and 'dark'."])
            }

            // Set the theme in session
            session.setAttribute('theme', theme)

            // Generate the URL to redirect back to the original page
            String redirectUrl = linkGenerator.link(uri: request.getHeader('Referer'))

            return ResponseEntity.ok(['theme': theme, 'redirectUrl': redirectUrl])
        } catch (Exception e) {
            // Log the exception and return a server error response
            log.error 'Error switching theme', e
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(['error': 'Server error while switching theme'])
        }
    }
}
