package by.it.group251002.nikonorova.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    private E[] elems = (E[]) new Object[]{};
    private int size = 0;
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String delimiter="";
        for (int i=0;i<size;i++){
            sb.append(delimiter).append(elems[i]);
            delimiter=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size==elems.length)
            elems= Arrays.copyOf(elems, (size * 3)/2+1);
        elems[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E elem= elems[index];
        System.arraycopy(elems,index+1,elems,index,size-index-1);
        size--;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size==elems.length)
            elems= Arrays.copyOf(elems, (size * 3)/2+1);
        System.arraycopy(elems,index,elems,index+1,size-index);
        elems[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1)
            remove(index);
        return (index > -1);
    }

    @Override
    public E set(int index, E element) {
        if (index>-1 && size>index) {
            E el = elems[index];
            elems[index] = element;
            return el;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            elems[i]=null;
        }
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0; i < size; i++) {
                if (elems[i]==null)
                    return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elems[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return elems[index];
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0;i<size;i++){
            if(elems[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex=-1;
        for(int i=0;i<size;i++){
            if(elems[i].equals(o)){
                lastIndex=i;
            }
        }
        return lastIndex;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object el:c){
            if(!this.contains(el)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.size()==0){
            return false;
        }
        for(Object el:c){
            this.add((E) el);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.size()==0){
            return false;
        }
        for(Object el:c){
            this.add(index++,(E) el);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed=false;
        for(Object el:c){
            while (this.contains(el)){
                this.remove((E) el);
                changed=true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed=false;
        for(int i=size-1;i>-1;i--){
            Object el=this.get(i);
            if(!c.contains(el)){
                this.remove(i);
                changed=true;
            }
        }
        return changed;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
