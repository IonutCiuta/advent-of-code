package com.ionutciuta.parsing;

import java.io.File;

public interface FileParser<T> {
    T parseFile(File file);
}
