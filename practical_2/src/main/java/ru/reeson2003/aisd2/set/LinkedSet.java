package ru.reeson2003.aisd2.set;

public interface LinkedSet<T> {

    static <T> LinkedSet<T> of(T... elements) {
        return new LinkedSetImpl<>(elements);
    }

    Element<T> head();

    void add(T element);

    interface Element<T> {
        T value();

        LinkedSetImpl.ElementImpl<T> next();
    }

    class LinkedSetImpl<T> implements LinkedSet<T> {
        private ElementImpl<T> head;

        LinkedSetImpl(T... elements) {
            for (int i = 0; i < elements.length; i++) {
                head = new ElementImpl<>(elements[i], head);
            }
        }

        @Override
        public Element<T> head() {
            return head;
        }

        @Override
        public void add(T element) {
            head = new ElementImpl<>(element, head);
        }

        static class ElementImpl<T> implements Element<T> {
            private T value;
            private ElementImpl<T> next;

            public ElementImpl(T value, ElementImpl<T> next) {
                this.value = value;
                this.next = next;
            }

            @Override
            public T value() {
                return value;
            }

            @Override
            public ElementImpl<T> next() {
                return next;
            }
        }
    }
}
