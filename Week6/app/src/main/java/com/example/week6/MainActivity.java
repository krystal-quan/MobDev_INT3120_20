package com.example.week6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonContext;

    Button buttonPopUp;

    Button buttonPopUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Week 6 Doing");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.item1)
                    Toast.makeText(MainActivity.this, "This is Item 1", Toast.LENGTH_SHORT).show();
                else if (itemId == R.id.item2)
                    Toast.makeText(MainActivity.this, "This is Item 2", Toast.LENGTH_SHORT).show();
                else if (itemId == R.id.item21)
                    Toast.makeText(MainActivity.this, "This is Item 2.1", Toast.LENGTH_SHORT).show();
                else if (itemId == R.id.item22)
                    Toast.makeText(MainActivity.this, "This is Item 2.2", Toast.LENGTH_SHORT).show();
                else if (itemId == R.id.itemg1)
                    Toast.makeText(MainActivity.this, "This is Item Group 1", Toast.LENGTH_SHORT).show();
                else if (itemId == R.id.itemg2)
                    Toast.makeText(MainActivity.this, "This is Item Group 2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //Context Here
        buttonContext = findViewById(R.id.button);
        registerForContextMenu(buttonContext);

        //Popup Here
        buttonPopUp = (Button) findViewById(R.id.button2);
        buttonPopUp2 = (Button) findViewById(R.id.button3);

        buttonPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonPopUp2);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Popup - " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Context Menu");
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle("Context Menu");
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1)
            Toast.makeText(MainActivity.this, "This is context Item 1", Toast.LENGTH_SHORT).show();
        else if (itemId == R.id.item2)
            Toast.makeText(MainActivity.this, "This is context Item 2", Toast.LENGTH_SHORT).show();
        else if (itemId == R.id.item21)
            Toast.makeText(MainActivity.this, "This is context Item 2.1", Toast.LENGTH_SHORT).show();
        else if (itemId == R.id.item22)
            Toast.makeText(MainActivity.this, "This is context Item 2.2", Toast.LENGTH_SHORT).show();
        else if (itemId == R.id.itemg1)
            Toast.makeText(MainActivity.this, "This is context Item Group 1", Toast.LENGTH_SHORT).show();
        else if (itemId == R.id.itemg2)
            Toast.makeText(MainActivity.this, "This is context Item Group 2", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}