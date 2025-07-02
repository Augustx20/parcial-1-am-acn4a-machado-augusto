package augusto.machado;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TareasActivity extends AppCompatActivity {

    private LinearLayout layoutTareas;
    private FirebaseFirestore db;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        layoutTareas = findViewById(R.id.layoutTareas);
        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cargarTareas();
    }

    private void cargarTareas() {
        db.collection("users").document(uid).collection("tareas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String titulo = doc.getString("titulo");
                        String fechaInicio = doc.getString("fechaInicio");
                        String fechaFin = doc.getString("fechaFin");

                        TextView tareaView = new TextView(this);
                        tareaView.setText("Título: " + titulo + "\nInicio: " + fechaInicio + "\nFin: " + fechaFin);
                        tareaView.setPadding(8, 16, 8, 16);

                        layoutTareas.addView(tareaView);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar tareas", Toast.LENGTH_SHORT).show();
                });
    }
}

