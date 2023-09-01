package com.example.PremiumFlights;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CRUDService
{
    public static String createAccountCRUD(CRUDaccounts crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=dbFirestore.collection("accounts").document(crud.getLogin()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static CRUDaccounts getAccountCRUD(String documentID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("accounts").document(documentID);
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document=future.get();
        CRUDaccounts crud;
        if(document.exists())
        {
            crud=document.toObject(CRUDaccounts.class);
            return crud;
        }
        return null;
    }

    public static String updateAccountCRUD(CRUDaccounts crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=dbFirestore.collection("accounts").document(crud.getLogin()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static String deleteAccountCRUD(String documentID)
    {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult=dbFirestore.collection("accounts").document(documentID).delete();
        return "Successfully deleted "+documentID;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static String createTicketCRUD(CRUDtickets crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=dbFirestore.collection("tickets").document(crud.getId()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static CRUDtickets getTicketCRUD(String documentID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("tickets").document(documentID);
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document=future.get();
        CRUDtickets crud;
        if(document.exists())
        {
            crud=document.toObject(CRUDtickets.class);
            return crud;
        }
        return null;
    }

    public static List<QueryDocumentSnapshot> getTicketDocumentsCRUD() throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("tickets").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents;
    }

}
