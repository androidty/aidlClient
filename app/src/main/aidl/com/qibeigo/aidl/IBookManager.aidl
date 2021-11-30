// IBookManager.aidl
package com.qibeigo.aidl;

// Declare any non-default types here with import statements
import com.qibeigo.aidl.Book;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getName();
    void addBook(in Book book);
    List<Book> getBookList();
    int addNum(int num1,int num2);

}
