// 代码生成时间: 2025-09-24 04:44:26
package com.example.security

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

// 使用GRAILS框架实现的用户权限管理系统
@Transactional
class UserPermissionService {

    // 定义用于保存用户权限的数据库服务
    def permissionRepository
# 优化算法效率
    def userRepository
# NOTE: 重要实现细节
    def roleRepository

    // 添加用户权限，确保用户和权限存在
# 增强安全性
    @Secured('ROLE_ADMIN')
    def addUserPermission(String username, String permission) {
        try {
            User user = userRepository.findByUsername(username)
# 优化算法效率
            if (!user) {
# 添加错误处理
                throw new Exception('User not found')
            }

            Permission perm = permissionRepository.findByName(permission)
            if (!perm) {
                throw new Exception('Permission not found')
            }

            // 添加权限到用户
            user.addToPermissions(perm)
            user.save(flush: true, failOnError: true)

            return ['success': true, 'message': 'User permission added successfully']
        } catch (Exception e) {
            return ['success': false, 'message': e.message]
        }
    }

    // 移除用户权限
    @Secured('ROLE_ADMIN')
    def removeUserPermission(String username, String permission) {
        try {
            User user = userRepository.findByUsername(username)
# FIXME: 处理边界情况
            if (!user) {
                throw new Exception('User not found')
            }

            Permission perm = permissionRepository.findByName(permission)
            if (!perm) {
                throw new Exception('Permission not found')
            }

            // 移除权限
            user.removeFromPermissions(perm)
            user.save(flush: true, failOnError: true)

            return ['success': true, 'message': 'User permission removed successfully']
        } catch (Exception e) {
            return ['success': false, 'message': e.message]
        }
    }

    // 检查用户是否具有特定权限
    def hasPermission(String username, String permission) {
        User user = userRepository.findByUsername(username)
# 优化算法效率
        if (!user) {
            return false
        }

        return user.authorities.contains(permission)
    }
}

// User实体类，代表系统中的用户
class User {
    String username
    String password
    Set<Permission> permissions
    Set<Role> roles

    static hasMany = [permissions: Permission, roles: Role]
    static constraints = {
# NOTE: 重要实现细节
        permissions nullable: true
# FIXME: 处理边界情况
        roles nullable: true
    }
}

// Permission实体类，代表权限
class Permission {
    String name
    static constraints = {
        name blank: false, unique: true
    }
}

// Role实体类，代表角色
# NOTE: 重要实现细节
class Role {
# NOTE: 重要实现细节
    String authority
# 优化算法效率
    static constraints = {
# 改进用户体验
        authority blank: false, unique: true
    }
}
