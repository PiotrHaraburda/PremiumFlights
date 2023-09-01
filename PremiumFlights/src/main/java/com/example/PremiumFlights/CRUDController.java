package com.example.PremiumFlights;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CRUDController
{
    public CRUDService crudService;

    public CRUDController(CRUDService crudService)
    {
        this.crudService=crudService;
    }

    @PostMapping("/create")
    public String createAccountCRUD(@RequestBody CRUDaccounts crud) throws InterruptedException, ExecutionException
    {
        return crudService.createAccountCRUD(crud);
    }

    @GetMapping("/get")
    public CRUDaccounts getAccountCRUD(@RequestParam String documentID) throws InterruptedException, ExecutionException
    {
        return crudService.getAccountCRUD(documentID);
    }

    @PutMapping("/update")
    public String updateAccountCRUD(@RequestBody CRUDaccounts crud) throws InterruptedException, ExecutionException
    {
        return crudService.updateAccountCRUD(crud);
    }

    @PutMapping("/delete")
    public String deleteAccountCRUD(@RequestParam String documentID)
    {
        return crudService.deleteAccountCRUD(documentID);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint()
    {
        return ResponseEntity.ok("Test Get Endpoint is Working");
    }
}
