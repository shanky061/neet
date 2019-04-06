**NHK**
A URL shorten-er service

TODO:
* Create basic endpoints -- DONE
* Add DB (MongoDB, MySQL, Postgres) -- MySQL, Postgres
* Add Caching (Redis)
* Add Queueing (Kafka)
* Add static code checking tools (checkstyle, spotBugs, errorProne)
* Logging (Log4j v2) + Monitoring (Data dog) + Tracing (??)

Long term TODO:
* Elastic Search
* RPC (gRPC)

* access logs ( server ) -- DONE
* server thread-pool configuration ( advanced - later )
* spring.http.log-request-details (in dev-tools, to expose sensitive information)
* management.trace.http - (In memory trace)
* analyze heapdump/threaddump

API:
* create tiny URL.

>> POST /tu
{
    payload: "https://www.google.com/"
}

<< 201 Created
{
    urlHash: "xyz"
}

* go to URL

>> GET /tu/xyz

<< 302 Moved Temporarily
Location: "https://www.google.com/"
