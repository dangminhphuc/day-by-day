## Sequence Input Streams

The `java.io.SequenceInputStream` class connects multiple input streams together in a particular order. A
SequenceInputStream first reads all the bytes from the first stream in the sequence, then all the bytes from the second
stream in the sequence, then all the bytes from the third stream, and so on. When the end of one stream is reached, that
stream is closed; the next data comes from the next stream. 