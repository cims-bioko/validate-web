# CIMS Validate Web

A web service for validating ODK-compliant xforms. It provides a means to validate ODK's xml-based xforms
using HTTP protocols. It is intended to be used by web applications, not by humans, and it only handles 
validation. It *does not* attempt to also handle conversion.

## Why?

The existing ODK tool, XLSForm Online, delivers an all-in-one conversion service for human users. Certain
characteristics, directly derived from that goal, makes it unsuitable for programmatic usage for other web
applications. This application is meant to provide a scalable, low-maintenance, 100% ODK compatible 
validation service suitable for these applications.

The above features are addressed as follows:

  * Scalable: no state is maintained between api requests allowing multiple servers to handle requests 
    without coordination
  * Low-maintenance: the api is based on the same javarosa library as the ODK tools and attempts to "go 
    with the flow" to rapidly handle ODK updates
  * 100% ODK compatible: the service is based on the same javarosa library specifically to decrease 
    the opportunity for incompatibilities

Rather than being bundled with XLSForm conversion, validation is handled separately because:

  * It is useful outside of XLSForm conversion
  * It requires a completely different runtime, Java, and its own dependencies
  * Deployed separately, both services can benefit from the same benefits listed above

 
## Requirements

To build this application, you'll need:

  * JDK 8+
  * Apache Maven
  * Docker

## Building

To build the application, you just need to run:

```
mvn clean install
```
