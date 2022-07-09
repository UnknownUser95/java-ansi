# ANSI codes for Java

This is an enum for quick access to ANSI codes. It also has multiple methods for quickly formatting a string to or creating an escape code for a given style.

## Notice

The output (likely a console) has to have ANSI escape code support. Otherwise you'll see a unicode symbol and the ANSI code, but no formatting.

Furthermore some consoles don't implement all escape codes. You'll just have to see what works for you.

For example: the standard console for the Eclipse IDE doesn't support any ANSI formatting. You'll have to install the "ANSI Escape in Console", or similar extensions, to use the formatting. But while it implements most codes, the slow blinking is not supported. Like I said, you'll have to test compatibility yourself.

Since version 1.2.0 you can now also just implement all methods as if the codes would work and disable or enable the formatters whenever you want via the setEnabled method.
