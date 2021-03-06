package top.jfunc.common.utils;

import top.jfunc.common.Editor;

import java.io.IOException;
import java.util.*;

public class Joiner {
  /**
   * Returns a joiner which automatically places {@code separator} between consecutive elements.
   */
  public static Joiner on(String separator) {
    return new Joiner(separator);
  }

  /**
   * Returns a joiner which automatically places {@code separator} between consecutive elements.
   */
  public static Joiner on(char separator) {
    return new Joiner(String.valueOf(separator));
  }

  private final String separator;

  private Joiner(String separator) {
    this.separator = Objects.requireNonNull(separator);
  }

  private Joiner(Joiner prototype) {
    this.separator = prototype.separator;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   */
  public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
    return appendTo(appendable, parts.iterator());
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   *
   * @since 11.0
   */
  public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
    Objects.requireNonNull(appendable);
    if (parts.hasNext()) {
      appendable.append(toString(parts.next()));
      while (parts.hasNext()) {
        appendable.append(separator);
        appendable.append(toString(parts.next()));
      }
    }
    return appendable;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   */
  public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
    return appendTo(appendable, Arrays.asList(parts));
  }

  /**
   * Appends to {@code appendable} the string representation of each of the remaining arguments.
   */
  public final <A extends Appendable> A appendTo(
      A appendable, Object first, Object second, Object... rest)
      throws IOException {
    return appendTo(appendable, iterable(first, second, rest));
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
    return appendTo(builder, parts.iterator());
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   *
   * @since 11.0
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
    try {
      appendTo((Appendable) builder, parts);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    }
    return builder;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   */
  public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
    return appendTo(builder, Arrays.asList(parts));
  }

  /**
   * Appends to {@code builder} the string representation of each of the remaining arguments.
   * Identical to {@link #appendTo(Appendable, Object, Object, Object...)}, except that it does not
   * throw {@link IOException}.
   */
  public final StringBuilder appendTo(
      StringBuilder builder, Object first, Object second, Object... rest) {
    return appendTo(builder, iterable(first, second, rest));
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   */
  public final String join(Iterable<?> parts) {
    return join(parts.iterator());
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   *
   * @since 11.0
   */
  public final String join(Iterator<?> parts) {
    return appendTo(new StringBuilder(), parts).toString();
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   */
  public final String join(Object[] parts) {
    return join(Arrays.asList(parts));
  }

  /**
   * Returns a string containing the string representation of each argument, using the previously
   * configured separator between each.
   */
  public final String join(Object first, Object second, Object... rest) {
    return join(iterable(first, second, rest));
  }

  /**
   * Returns a joiner with the same behavior as this one, except automatically substituting {@code
   * nullText} for any provided null elements.
   */
  public Joiner useForNull(final String nullText) {
    Objects.requireNonNull(nullText);
    return new Joiner(this) {
      @Override
      CharSequence toString(Object part) {
        return (part == null) ? nullText : Joiner.this.toString(part);
      }

      @Override
      public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified useForNull");
      }

      @Override
      public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
      }
    };
  }

  /**
   * Returns a joiner with the same behavior as this joiner, except automatically skipping over any
   * provided null elements.
   */
  public Joiner skipNulls() {
    return new Joiner(this) {
      @Override
      public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Objects.requireNonNull(appendable, "appendable");
        Objects.requireNonNull(parts, "parts");
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(Joiner.this.toString(part));
            break;
          }
        }
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(separator);
            appendable.append(Joiner.this.toString(part));
          }
        }
        return appendable;
      }

      @Override
      public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified skipNulls");
      }

      @Override
      public MapJoiner withKeyValueSeparator(String kvs) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
      }
    };
  }

  /**
   * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
   * this {@code Joiner} otherwise.
   *
   * @since 20.0
   */
  public MapJoiner withKeyValueSeparator(char keyValueSeparator) {
    return withKeyValueSeparator(String.valueOf(keyValueSeparator));
  }

  /**
   * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
   * this {@code Joiner} otherwise.
   */
  public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
      return new MapJoiner(this, keyValueSeparator);
  }

  /**
   * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
   * this {@code Joiner} otherwise.
   */
  public <T> MapJoiner withKeyValueSeparator(String keyValueSeparator, Editor<T> valueEditor) {
      return new MapJoiner(this, keyValueSeparator, valueEditor);
  }

  /**
   * An object that joins map entries in the same manner as {@code Joiner} joins iterables and
   * arrays. Like {@code Joiner}, it is thread-safe and immutable.
   *
   * <p>In addition to operating on {@code Map} instances, {@code MapJoiner} can operate on {@code
   * Multimap} entries in two distinct modes:
   *
   * <ul>
   * <li>To output a separate entry for each key-value pair, pass {@code multimap.entries()} to a
   * {@code MapJoiner} method that accepts entries as input, and receive output of the form
   * {@code key1=A&key1=B&key2=C}.
   * <li>To output a single entry for each key, pass {@code multimap.asMap()} to a {@code MapJoiner}
   * method that accepts a map as input, and receive output of the form {@code
   *     key1=[A, B]&key2=C}.
   * </ul>
   *
   * @since 2.0
   */
  public static final class MapJoiner {
    private final Joiner joiner;
    private final String keyValueSeparator;
    /**默认的不转换，原样返回*/
    private Editor valueEditor = (v)-> v;

    private MapJoiner(Joiner joiner, String keyValueSeparator) {
      // only "this" is ever passed, so don't checkNotNull
      this.joiner = joiner;
      this.keyValueSeparator = Objects.requireNonNull(keyValueSeparator);
    }

      public <T> MapJoiner setValueEditor(Editor<T> valueEditor) {
          this.valueEditor = valueEditor;
          return this;
      }

      private <T> MapJoiner(Joiner joiner, String keyValueSeparator, Editor<T> valueEditor) {
      this.joiner = joiner;
      this.keyValueSeparator = Objects.requireNonNull(keyValueSeparator);
      this.valueEditor = Objects.requireNonNull(valueEditor);
    }

    /**
     * Appends the string representation of each entry of {@code map}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     */
    public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
      return appendTo(appendable, map.entrySet());
    }

    /**
     * Appends the string representation of each entry of {@code map}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Map)}, except that it does not throw {@link IOException}.
     */
    public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
      return appendTo(builder, map.entrySet());
    }

    /**
     * Returns a string containing the string representation of each entry of {@code map}, using the
     * previously configured separator and key-value separator.
     */
    public String join(Map<?, ?> map) {
      return join(map.entrySet());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     *
     * @since 10.0
     */
    public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?, ?>> entries)
        throws IOException {
      return appendTo(appendable, entries.iterator());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     *
     * @since 11.0
     */
    public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?, ?>> parts)
        throws IOException {
      Objects.requireNonNull(appendable);
      if (parts.hasNext()) {
        Map.Entry<?, ?> entry = parts.next();
        appendable.append(joiner.toString(entry.getKey()));
        appendable.append(keyValueSeparator);
        appendable.append(joiner.toString(valueEditor.edit(entry.getValue())));
        while (parts.hasNext()) {
          appendable.append(joiner.separator);
          Map.Entry<?, ?> e = parts.next();
          appendable.append(joiner.toString(e.getKey()));
          appendable.append(keyValueSeparator);
          appendable.append(joiner.toString(valueEditor.edit(e.getValue())));
        }
      }
      return appendable;
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
     *
     * @since 10.0
     */
    public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Map.Entry<?, ?>> entries) {
      return appendTo(builder, entries.iterator());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
     *
     * @since 11.0
     */
    public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Map.Entry<?, ?>> entries) {
      try {
        appendTo((Appendable) builder, entries);
      } catch (IOException impossible) {
        throw new AssertionError(impossible);
      }
      return builder;
    }

    /**
     * Returns a string containing the string representation of each entry in {@code entries}, using
     * the previously configured separator and key-value separator.
     *
     * @since 10.0
     */
    public String join(Iterable<? extends Map.Entry<?, ?>> entries) {
      return join(entries.iterator());
    }

    /**
     * Returns a string containing the string representation of each entry in {@code entries}, using
     * the previously configured separator and key-value separator.
     *
     * @since 11.0
     */
    public String join(Iterator<? extends Map.Entry<?, ?>> entries) {
      return appendTo(new StringBuilder(), entries).toString();
    }

    /**
     * Returns a map joiner with the same behavior as this one, except automatically substituting
     * {@code nullText} for any provided null keys or values.
     */
    public MapJoiner useForNull(String nullText) {
      return new MapJoiner(joiner.useForNull(nullText), keyValueSeparator,this.valueEditor);
    }
  }

  CharSequence toString(Object part) {
    Objects.requireNonNull(part); // checkNotNull for GWT (do not optimize).
    return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
  }

  private static Iterable<Object> iterable(
      final Object first, final Object second, final Object[] rest) {
    Objects.requireNonNull(rest);
    return new AbstractList<Object>() {
      @Override
      public int size() {
        return rest.length + 2;
      }

      @Override
      public Object get(int index) {
        switch (index) {
          case 0:
            return first;
          case 1:
            return second;
          default:
            return rest[index - 2];
        }
      }
    };
  }
}