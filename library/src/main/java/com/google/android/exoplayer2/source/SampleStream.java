/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;

/**
 * A stream of media samples (and associated format information).
 */
public interface SampleStream {

  /**
   * Returns whether data is available to be read.
   * <p>
   * Note: If the stream has ended then a buffer with the end of stream flag can always be read from
   * {@link #readData(FormatHolder, DecoderInputBuffer)}. Hence an ended stream is always ready.
   *
   * @return Whether data is available to be read.
   */
  boolean isReady();

  /**
   * Throws an error that's preventing data from being read. Does nothing if no such error exists.
   *
   * @throws IOException The underlying error.
   */
  void maybeThrowError() throws IOException;

  /**
   * Attempts to read from the stream.
   *
   * @param formatHolder A {@link FormatHolder} to populate in the case of reading a format.
   * @param buffer A {@link DecoderInputBuffer} to populate in the case of reading a sample or the
   *     end of the stream. If the end of the stream has been reached, the
   *     {@link C#BUFFER_FLAG_END_OF_STREAM} flag will be set on the buffer.
   * @return The result, which can be {@link C#RESULT_NOTHING_READ}, {@link C#RESULT_FORMAT_READ} or
   *     {@link C#RESULT_BUFFER_READ}.
   */
  int readData(FormatHolder formatHolder, DecoderInputBuffer buffer);

  /**
   * Attempts to skip to the keyframe before the specified time.
   *
   * @param timeUs The specified time.
   */
  void skipToKeyframeBefore(long timeUs);

}