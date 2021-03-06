/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.testing.compile;

import static org.truth0.Truth.ASSERT;

import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.TreeVisitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EqualityScannerTest {
  @Test
  public void nullLiterals_canBeTestedForEquality() {
    EqualityScanner scanner = new EqualityScanner();

    LiteralTree nullLiteral = new SimpleLiteralTree(null, Kind.NULL_LITERAL);
    LiteralTree valueLiteral = new SimpleLiteralTree("Hi", Kind.STRING_LITERAL);

    ASSERT.that(scanner.visitLiteral(nullLiteral, nullLiteral)).isTrue();
    ASSERT.that(scanner.visitLiteral(nullLiteral, valueLiteral)).isFalse();
    ASSERT.that(scanner.visitLiteral(valueLiteral, nullLiteral)).isFalse();
  }

  private static class SimpleLiteralTree implements LiteralTree {
    final Object value;
    final Kind kind;

    SimpleLiteralTree(Object value, Kind kind) {
      this.value = value;
      this.kind = kind;
    }

    @Override public Object getValue() {
      return value;
    }

    @Override public Kind getKind() {
      return kind;
    }

    @Override public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
      throw new UnsupportedOperationException();
    }
  }
}
