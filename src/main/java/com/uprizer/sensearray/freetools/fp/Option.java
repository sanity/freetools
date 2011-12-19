package com.uprizer.sensearray.freetools.fp;

import com.google.common.base.Function;

public abstract class Option<T> {

	public static <T> Option<T> none() {
		return new Option.None<T>();
	}

	public static <T> Option<T> some(final T value) {
		return new Option.Some<T>(value);
	}

	public abstract boolean isNone();

	public abstract boolean isSome();

	public abstract <G> Option<G> transform(Function<T, G> function);

	public abstract T orSome(T alternate);

	public abstract T some();

	public static class None<T> extends Option<T> {

		@Override
		public boolean isNone() {
			return true;
		}

		@Override
		public boolean isSome() {
			return false;
		}

		@Override
		public <G> Option<G> transform(final Function<T, G> function) {
			return none();
		}

		@Override
		public T orSome(final T alternate) {
			return alternate;
		}

		@Override
		public T some() {
			throw new RuntimeException("Called some() on None");
		}

	}

	public static class Some<T> extends Option<T> {

		private final T value;

		public Some(final T value) {
			this.value = value;
		}

		@Override
		public boolean isNone() {
			return false;
		}

		@Override
		public boolean isSome() {
			return true;
		}

		@Override
		public <G> Option<G> transform(final Function<T, G> function) {
			return some(function.apply(value));
		}

		@Override
		public T orSome(final T alternate) {
			return value;
		}

		@Override
		public T some() {
			return value;
		}

	}
}

