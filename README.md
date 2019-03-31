# CIMS Validate Web

A web service for validating ODK-compliant xforms. It provides a means to validate ODK's xml-based xforms
using HTTP protocols. It is intended to be used by web applications, not by humans, and it only handles 
validation. It *does not* attempt to also handle conversion.

## Why?

The existing ODK tool, XLSForm Online, delivers an all-in-one conversion service for human users. Certain
characteristics, directly derived from that goal, makes it unsuitable for programmatic usage for other web
applications. This application is meant to provide a high-performance, highly-scalable, validation service
suitable for these types of applications.
 
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
