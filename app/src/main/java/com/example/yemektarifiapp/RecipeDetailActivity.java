package com.example.yemektarifiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView recipeImageView;
    private TextView recipeDetailTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeImageView = findViewById(R.id.recipeImageView);
        recipeDetailTextView = findViewById(R.id.recipeDetailTextView);
        backButton = findViewById(R.id.backButton);

        String recipeName = getIntent().getStringExtra("recipeName");
        int imageResId = getIntent().getIntExtra("imageResId", -1);
        String recipeDetail = getRecipeDetail(recipeName);

        recipeImageView.setImageResource(imageResId);
        recipeDetailTextView.setText(recipeDetail);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Aktiviteyi bitir ve geri dön
            }
        });
    }

    private String getRecipeDetail(String recipeName) {
        // Detayları burada döndürebilirsiniz
        switch (recipeName) {
            case "Spaghetti Bolognese":
                return "Malzemeler:\n- 200g Spagetti\n- 100g Kıyma\n- 1 Soğan\n- 2 Diş Sarımsak\n- 400g Domates\n- Tuz, Karabiber\n\nYapılışı:\n1. Spagettiyi haşlayın.\n2. Soğan ve sarımsağı kavurun.\n3. Kıymayı ekleyin ve pişirin.\n4. Domatesleri ekleyin ve sosu hazırlayın.\n5. Spagettiyi sosla karıştırın ve servis yapın.";
            case "Chicken Curry":
                return "Malzemeler:\n- 500g Tavuk Göğsü\n- 1 Soğan\n- 2 Diş Sarımsak\n- 1 Yemek Kaşığı Köri\n- 400ml Hindistan Cevizi Sütü\n- Tuz, Karabiber\n\nYapılışı:\n1. Soğan ve sarımsağı kavurun.\n2. Tavukları ekleyin ve pişirin.\n3. Köri ve hindistan cevizi sütünü ekleyin.\n4. Kaynatın ve kısık ateşte pişirin.\n5. Pilav ile servis yapın.";
            case "Beef Stroganoff":
                return "Malzemeler:\n- 500g Dana Eti\n- 1 Soğan\n- 200g Mantar\n- 200ml Krema\n- 1 Yemek Kaşığı Hardal\n- Tuz, Karabiber\n\nYapılışı:\n1. Soğanı ve mantarları kavurun.\n2. Dana etini ekleyin ve pişirin.\n3. Krema ve hardalı ekleyin.\n4. Kaynatın ve kısık ateşte pişirin.\n5. Makarnayla servis yapın.";
            case "Vegetable Stir Fry":
                return "Malzemeler:\n- 1 Kırmızı Biber\n- 1 Yeşil Biber\n- 1 Havuç\n- 1 Kabak\n- 100g Brokoli\n- 2 Yemek Kaşığı Soya Sosu\n- Tuz, Karabiber\n\nYapılışı:\n1. Sebzeleri ince ince doğrayın.\n2. Tavada sebzeleri soteleyin.\n3. Soya sosunu ekleyin ve karıştırın.\n4. Sebzeler yumuşayınca ocaktan alın.\n5. Pilav veya noodle ile servis yapın.";
            case "Tacos":
                return "Malzemeler:\n- 500g Kıyma\n- 1 Soğan\n- 1 Paket Taco Baharatı\n- 8 Adet Tortilla\n- Marul, Domates, Kaşar Peyniri\n- Salsa Sosu\n\nYapılışı:\n1. Soğanı kavurun.\n2. Kıymayı ekleyin ve pişirin.\n3. Taco baharatını ekleyin ve karıştırın.\n4. Tortillaları ısıtın.\n5. Kıymalı harcı tortillaların içine koyun, üzerine marul, domates ve kaşar peyniri ekleyin.\n6. Salsa sosu ile servis yapın.";
            case "Sushi":
                return "Malzemeler:\n- 200g Sushi Pirinci\n- 2 Yemek Kaşığı Pirinç Sirkesi\n- 100g Taze Somon\n- 1 Avokado\n- 1 Salatalık\n- Nori Yaprağı\n- Wasabi, Soya Sosu\n\nYapılışı:\n1. Sushi pirincini haşlayın ve pirinç sirkesi ile karıştırın.\n2. Nori yaprağını serin ve üzerine pirinci yayın.\n3. İnce dilimlenmiş somon, avokado ve salatalığı ekleyin.\n4. Rulo yaparak sarın ve dilimleyin.\n5. Wasabi ve soya sosu ile servis yapın.";
            case "Pizza Margherita":
                return "Malzemeler:\n- 1 Pizza Hamuru\n- 200g Domates Sosu\n- 200g Mozzarella Peyniri\n- Taze Fesleğen\n- Zeytinyağı\n- Tuz\n\nYapılışı:\n1. Pizza hamurunu açın.\n2. Üzerine domates sosunu yayın.\n3. Mozzarella peynirini serpin.\n4. Taze fesleğen yapraklarını ekleyin.\n5. Zeytinyağı gezdirin ve tuz serpin.\n6. 200 derece fırında 15-20 dakika pişirin.";
            default:
                return "Tarif bulunamadı.";
        }
    }
}