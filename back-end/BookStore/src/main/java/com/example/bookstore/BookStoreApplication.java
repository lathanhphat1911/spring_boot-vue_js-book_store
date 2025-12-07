package com.example.bookstore;

import com.example.bookstore.Controller.StoreController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class BookStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);

	}
}
