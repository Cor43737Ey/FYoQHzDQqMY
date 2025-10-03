// 代码生成时间: 2025-10-03 22:33:04
package com.example.game.save

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

/**
 * Service class to handle game save operations.
 * This class provides methods to save, load, and delete game save files.
 */
@Transactional
@TypeChecked
class GameSaveService {

    /**
     * Saves the game state to a file.
     *
     * @param gameState The game state to be saved.
     * @param fileName The name of the save file.
     * @return A map containing the file path and a success status.
     */
    Map<String, Object> saveGame(String gameState, String fileName) {
        try {
            // Ensure the file name is valid
            if (fileName.isEmpty() || !fileName.matches('^[A-Za-z0-9_\-]+\.gsm$')) {
                throw new IllegalArgumentException('Invalid file name')
            }

            // Save the game state to a file
            def filePath = "/path/to/saves/${fileName}"
            new File(filePath).write gameState
            return [filePath: filePath, success: true]
        } catch (Exception e) {
            log.error 'Error saving game state', e
            return [success: false, message: 'Failed to save game state']
        }
    }

    /**
     * Loads the game state from a file.
     *
     * @param fileName The name of the save file.
     * @return A map containing the game state and a success status.
     */
    Map<String, Object> loadGame(String fileName) {
        try {
            // Ensure the file name is valid
            if (fileName.isEmpty() || !fileName.matches('^[A-Za-z0-9_\-]+\.gsm$')) {
                throw new IllegalArgumentException('Invalid file name')
            }

            // Load the game state from a file
            def filePath = "/path/to/saves/${fileName}"
            return [gameState: new File(filePath).text, success: true]
        } catch (FileNotFoundException e) {
            log.error 'Save file not found', e
            return [success: false, message: 'Save file not found']
        } catch (Exception e) {
            log.error 'Error loading game state', e
            return [success: false, message: 'Failed to load game state']
        }
    }

    /**
     * Deletes a game save file.
     *
     * @param fileName The name of the save file.
     * @return A map containing a success status and a message.
     */
    Map<String, Object> deleteSave(String fileName) {
        try {
            // Ensure the file name is valid
            if (fileName.isEmpty() || !fileName.matches('^[A-Za-z0-9_\-]+\.gsm$')) {
                throw new IllegalArgumentException('Invalid file name')
            }

            // Delete the save file
            def filePath = "/path/to/saves/${fileName}"
            new File(filePath).delete()
            return [success: true, message: 'Save file deleted']
        } catch (FileNotFoundException e) {
            log.error 'Save file not found', e
            return [success: false, message: 'Save file not found']
        } catch (Exception e) {
            log.error 'Error deleting save file', e
            return [success: false, message: 'Failed to delete save file']
        }
    }
}
