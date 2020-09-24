package com.qibeigo.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] books = {"西游记", "三国演义", "红楼梦", "水浒传"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("com.qibeigo.aidlserver.MyService");
        intent.setPackage("com.qibeigo.aidlserver");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    IBookManager bookManager;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public void click(View view) throws RemoteException {
        if (view.getId() == R.id.btn) {
            Toast.makeText(this, bookManager.getName(), Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btn1) {//添加书籍
            int index = new Random().nextInt(4);
            Book book = new Book(books[index]);
            bookManager.addBook(book);
        } else if (view.getId() == R.id.btn2) {
            List<Book> books = bookManager.getBookList();
            Log.d("getbooks", books.toString());
            Toast.makeText(this, books.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}