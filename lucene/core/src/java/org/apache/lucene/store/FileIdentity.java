/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.store;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 */
public class FileIdentity {
    FileTime creationTime = null;
    Object fileKey = null;

    public FileIdentity(Path path) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        fileKey = attributes.fileKey();
        if (fileKey == null) {
            creationTime = attributes.creationTime();
        }
    }

    public boolean isSameIdentity(FileIdentity other) {
        return (this.creationTime == null || this.creationTime.equals(other.creationTime)) &&
                (this.fileKey == null || this.fileKey.equals(other.fileKey));
    }

    @Override
    public String toString() {
        return String.format("Identity { fileKey: %s, creationTime: %s }", this.fileKey, this.creationTime);
    }
}
