package ru.geekbrains.notes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import ru.geekbrains.notes.R;
import ru.geekbrains.notes.domain.Notes;

public class MainActivity extends AppCompatActivity implements ListFragment.OnNoteClicked {

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ToolBar");

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_one) {
                    Toast.makeText(MainActivity.this, "Добавили", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (!isLandscape) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = fragmentManager.findFragmentById(R.id.container);

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ListFragment())
                        .commit();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onNoteClicked(Notes notes) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (isLandscape) {
            fragmentManager.beginTransaction()
                    .replace(R.id.details_fragment, DetailsFragment.newInstance(notes))
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container,DetailsFragment.newInstance(notes))
                    .addToBackStack(null)
                    .commit();
        }
    }

}