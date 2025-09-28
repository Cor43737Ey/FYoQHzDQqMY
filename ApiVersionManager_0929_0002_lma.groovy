// 代码生成时间: 2025-09-29 00:02:30
class ApiVersionManager {

    // A map to store API versions, where the key is the API name and the value is the version number.
    private Map<String, String> apiVersions = new HashMap<>()

    /**
     * Registers an API version.
     * 
     * @param apiName The name of the API.
     * @param version The version number of the API.
     * @return true if the registration was successful, false otherwise.
     */
    boolean registerApiVersion(String apiName, String version) {
        try {
            if (apiName && version) {
                apiVersions.put(apiName, version)
                return true
            } else {
                throw new IllegalArgumentException('API name and version cannot be null or empty.')
            }
        } catch (IllegalArgumentException e) {
            // Log the error and return false
            log.error("Error registering API version: \${e.message}")
            return false
        }
    }

    /**
     * Deregisters an API version.
     * 
     * @param apiName The name of the API.
     * @return true if the deregistration was successful, false otherwise.
     */
    boolean deregisterApiVersion(String apiName) {
        try {
            if (apiVersions.remove(apiName) != null) {
                return true
            } else {
                throw new IllegalArgumentException("API version for \${apiName} does not exist.")
            }
        } catch (IllegalArgumentException e) {
            // Log the error and return false
            log.error("Error deregistering API version: \${e.message}")
            return false
        }
    }

    /**
     * Retrieves the version of an API.
     * 
     * @param apiName The name of the API.
     * @return The version number of the API, or null if not found.
     */
    String getApiVersion(String apiName) {
        return apiVersions.get(apiName)
    }

    // Additional methods can be added here for further API version management functionality.

}
