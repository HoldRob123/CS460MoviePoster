package com.example.poster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity is the main entry point of the application, responsible for initializing
 * the UI and managing user interactions with the poster list.
 */
public class MainActivity extends AppCompatActivity implements PostersListener{

    private Button buttonAddToWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RecyclerView postersRecyclerView = findViewById(R.id.postersRecylerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);

        // Prepare data for the poster list
        List<Poster> posterList = new ArrayList<>();

        Poster poster1 = new Poster();
        poster1.image = R.drawable.movieposter1;
        poster1.name ="Back To The Future";
        poster1.createdBy ="Robert Zemickis";
        poster1.rating = 4.8f;
        poster1.story ="Back to the Future is about Marty McFly, a teen who time-travels to 1955 and must secure his parents' romance to save his future.";
        posterList.add(poster1);

        Poster poster2 = new Poster();
        poster2.image = R.drawable.movieposter2;
        poster2.name ="The Dark Knight";
        poster2.createdBy ="Christopher Nolan";
        poster2.rating = 4.9f;
        poster2.story ="The Dark Knight follows Batman as he battles the Joker, a chaotic criminal mastermind who seeks to plunge Gotham City into anarchy.";
        posterList.add(poster2);

        Poster poster3 = new Poster();
        poster3.image = R.drawable.movieposter3;
        poster3.name ="The Matrix";
        poster3.createdBy ="Lana Wachowski";
        poster3.rating = 4.3f;
        poster3.story ="The Matrix follows Neo, a hacker who discovers that reality is a simulated world controlled by machines, leading him to join a rebellion to free humanity.";
        posterList.add(poster3);

        Poster poster4 = new Poster();
        poster4.image = R.drawable.movieposter4;
        poster4.name ="E.T.";
        poster4.createdBy ="Steven Spielberg";
        poster4.rating = 4.7f;
        poster4.story ="E.T. the Extra-Terrestrial tells the story of a young boy who befriends a stranded alien and helps him return home while evading government capture.";
        posterList.add(poster4);

        Poster poster5 = new Poster();
        poster5.image = R.drawable.movieposter5;
        poster5.name ="Home Alone";
        poster5.createdBy ="Chris Columbus";
        poster5.rating = 4.8f;
        poster5.story ="Home Alone is a comedy about a young boy who is accidentally left behind when his family goes on vacation, and must defend his home from two bumbling burglars.";
        posterList.add(poster5);

        Poster poster6 = new Poster();
        poster6.image = R.drawable.movieposter6;
        poster6.name ="Star Wars";
        poster6.createdBy ="George Lucas";
        poster6.rating = 4.6f;
        poster6.story ="\n" +
                "Star Wars is a space opera about a young farm boy who joins a rebellion against an evil empire, discovering his destiny as a Jedi Knight along the way.";
        posterList.add(poster6);

        Poster poster7 = new Poster();
        poster7.image = R.drawable.movieposter7;
        poster7.name ="Jurassic Park";
        poster7.createdBy ="Steven Spielberg";
        poster7.rating = 4.7f;
        poster7.story ="Jurassic Park is an adventure film where cloned dinosaurs run amok in a theme park, leading to a thrilling fight for survival.";
        posterList.add(poster7);

        Poster poster8 = new Poster();
        poster8.image = R.drawable.movieposter8;
        poster8.name ="Scream";
        poster8.createdBy ="Wes Craven";
        poster8.rating = 4.5f;
        poster8.story ="Scream is a horror film where a masked killer terrorizes a small town, blending suspense with dark humor.";
        posterList.add(poster8);

        Poster poster9 = new Poster();
        poster9.image = R.drawable.movieposter9;
        poster9.name ="Top Gun";
        poster9.createdBy ="Tony Scott";
        poster9.rating = 4.5f;
        poster9.story ="Top Gun is an action film about elite Navy fighter pilots, featuring high-flying aerial combat and intense rivalries.";
        posterList.add(poster9);

        Poster poster10 = new Poster();
        poster10.image = R.drawable.movieposter10;
        poster10.name ="GoodFellas";
        poster10.createdBy ="Martin Scorsese";
        poster10.rating = 4.7f;
        poster10.story ="Goodfellas is a crime drama that chronicles the rise and fall of mob associate Henry Hill and his life in the Mafia.";
        posterList.add(poster10);

        // Set up the RecyclerView with the PosterAdapter
        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        // Set listener for the Add to Watchlist button
        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();
                
                StringBuilder posterNames = new StringBuilder();
                for (int i=0;i<selectPosters.size();i++){
                    if (i==0){
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onPosterAction(boolean isSelected) {

        // Show or hide the add to watchlist button based on poster selection
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}