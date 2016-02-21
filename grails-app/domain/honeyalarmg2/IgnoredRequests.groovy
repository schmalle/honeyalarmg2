package honeyalarmg2

class IgnoredRequests {

    //
    // defines requests, which are not relevant and may not be shown again
    //

    String request
    String checksum

    static constraints = {
        request (nullable: false)
        checksum(nullable: false)
    }
}
