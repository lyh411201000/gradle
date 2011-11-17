/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.file.archive.decompressors;

import org.gradle.api.tasks.bundling.Decompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * by Szczepan Faber, created at: 11/16/11
 */
public class NoOpDecompressor implements Decompressor {
    public InputStream decompress(File file) {
        try {
            return new FileInputStream(file);
        } catch (Exception e) {
            String message = String.format("Unable to create input stream for file: %s due to: %s.", file.getName(), e.getMessage());
            throw new RuntimeException(message, e);
        }
    }
}