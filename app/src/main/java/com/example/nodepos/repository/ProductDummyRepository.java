package com.example.nodepos.repository;

import com.example.nodepos.R;
import com.example.nodepos.model.CategoryModel;
import com.example.nodepos.model.productModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDummyRepository {
    public static List<productModel> getProductDummyList() {
        List<productModel> productList = new ArrayList<>();
        // ================= Kebutuhan Ibu & Anak (P001) =================
        productList.add(new productModel("PRD001", "Popok Bayi", "P0001", "Kebutuhan Ibu & Anak", 80000, "https://picsum.photos/200", 15, 0));
        productList.add(new productModel("PRD002", "Susu Formula", "P0001", "Kebutuhan Ibu & Anak", 95000, "https://picsum.photos/201", 15, 0));
        productList.add(new productModel("PRD003", "Tisu Basah", "P0001", "Kebutuhan Ibu & Anak", 15000, "https://picsum.photos/202", 15, 0));
        productList.add(new productModel("PRD004", "Mainan Edukasi", "P0001", "Kebutuhan Ibu & Anak", 50000, "https://picsum.photos/203", 15, 0));
        productList.add(new productModel("PRD005", "Baju Bayi", "P0001", "Kebutuhan Ibu & Anak", 60000, "https://picsum.photos/204", 15, 0));
        productList.add(new productModel("PRD006", "Dot Bayi", "P0001", "Kebutuhan Ibu & Anak", 20000, "https://picsum.photos/205", 15, 0));
        productList.add(new productModel("PRD007", "Kereta Dorong", "P0001", "Kebutuhan Ibu & Anak", 350000, "https://picsum.photos/206", 15, 0));
        productList.add(new productModel("PRD008", "Botol Susu", "P0001", "Kebutuhan Ibu & Anak", 25000, "https://picsum.photos/207", 15, 0));
        productList.add(new productModel("PRD009", "Perlak Bayi", "P0001", "Kebutuhan Ibu & Anak", 30000, "https://picsum.photos/208", 15, 0));
        productList.add(new productModel("PRD010", "Bedong Bayi", "P0001", "Kebutuhan Ibu & Anak", 40000, "https://picsum.photos/209", 15, 0));

// ================= Produk Segar & Beku (P0002) =================
        productList.add(new productModel("PRD011", "Daging Sapi", "P0002", "Produk Segar & Beku", 120000, "https://picsum.photos/210", 15, 0));
        productList.add(new productModel("PRD012", "Ayam Fillet", "P0002", "Produk Segar & Beku", 45000, "https://picsum.photos/211", 15, 0));
        productList.add(new productModel("PRD013", "Ikan Salmon", "P0002", "Produk Segar & Beku", 95000, "https://picsum.photos/212", 15, 0));
        productList.add(new productModel("PRD014", "Sayur Bayam", "P0002", "Produk Segar & Beku", 15000, "https://picsum.photos/213", 15, 0));
        productList.add(new productModel("PRD015", "Kentang", "P0002", "Produk Segar & Beku", 20000, "https://picsum.photos/214", 15, 0));
        productList.add(new productModel("PRD016", "Jagung", "P0002", "Produk Segar & Beku", 10000, "https://picsum.photos/215", 15, 0));
        productList.add(new productModel("PRD017", "Keju", "P0002", "Produk Segar & Beku", 40000, "https://picsum.photos/216", 15, 0));
        productList.add(new productModel("PRD018", "Es Krim", "P0002", "Produk Segar & Beku", 35000, "https://picsum.photos/217", 15, 0));
        productList.add(new productModel("PRD019", "Sosis", "P0002", "Produk Segar & Beku", 25000, "https://picsum.photos/218", 15, 0));
        productList.add(new productModel("PRD020", "Bakso Beku", "P0002", "Produk Segar & Beku", 30000, "https://picsum.photos/219", 15, 0));

// ================= Minuman (P0003) =================
        productList.add(new productModel("PRD021", "Kopi Hitam", "P0003", "Minuman", 15000, "https://picsum.photos/220", 15, 0));
        productList.add(new productModel("PRD022", "Teh Botol", "P0003", "Minuman", 8000, "https://picsum.photos/221", 15, 0));
        productList.add(new productModel("PRD023", "Jus Jeruk", "P0003", "Minuman", 12000, "https://picsum.photos/222", 15, 0));
        productList.add(new productModel("PRD024", "Susu Coklat", "P0003", "Minuman", 10000, "https://picsum.photos/223", 15, 0));
        productList.add(new productModel("PRD025", "Air Mineral", "P0003", "Minuman", 5000, "https://picsum.photos/224", 15, 0));
        productList.add(new productModel("PRD026", "Lemon Tea", "P0003", "Minuman", 9000, "https://picsum.photos/225", 15, 0));
        productList.add(new productModel("PRD027", "Kopi Susu", "P0003", "Minuman", 16000, "https://picsum.photos/226", 15, 0));
        productList.add(new productModel("PRD028", "Teh Hijau", "P0003", "Minuman", 11000, "https://picsum.photos/227", 15, 0));
        productList.add(new productModel("PRD029", "Soda", "P0003", "Minuman", 7000, "https://picsum.photos/228", 15, 0));
        productList.add(new productModel("PRD030", "Smoothie Mangga", "P0003", "Minuman", 18000, "https://picsum.photos/229", 15, 0));

// ================= Kebutuhan Rumah Tangga (P0004) =================
        productList.add(new productModel("PRD031", "Detergen Cair", "P0004", "Kebutuhan Rumah Tangga", 25000, "https://picsum.photos/230", 15, 0));
        productList.add(new productModel("PRD032", "Sabun Cuci Piring", "P0004", "Kebutuhan Rumah Tangga", 15000, "https://picsum.photos/231", 15, 0));
        productList.add(new productModel("PRD033", "Pembersih Lantai", "P0004", "Kebutuhan Rumah Tangga", 20000, "https://picsum.photos/232", 15, 0));
        productList.add(new productModel("PRD034", "Sapu Lidi", "P0004", "Kebutuhan Rumah Tangga", 12000, "https://picsum.photos/233", 15, 0));
        productList.add(new productModel("PRD035", "Keset", "P0004", "Kebutuhan Rumah Tangga", 18000, "https://picsum.photos/234", 15, 0));
        productList.add(new productModel("PRD036", "Tempat Sampah", "P0004", "Kebutuhan Rumah Tangga", 35000, "https://picsum.photos/235", 15, 0));
        productList.add(new productModel("PRD037", "Lap Serbaguna", "P0004", "Kebutuhan Rumah Tangga", 15000, "https://picsum.photos/236", 15, 0));
        productList.add(new productModel("PRD038", "Gantungan Baju", "P0004", "Kebutuhan Rumah Tangga", 10000, "https://picsum.photos/237", 15, 0));
        productList.add(new productModel("PRD039", "Panci", "P0004", "Kebutuhan Rumah Tangga", 50000, "https://picsum.photos/238", 15, 0));
        productList.add(new productModel("PRD040", "Wajan", "P0004", "Kebutuhan Rumah Tangga", 45000, "https://picsum.photos/239", 15, 0));

// ================= Pet Food (P0005) =================
        productList.add(new productModel("PRD041", "Makanan Kucing", "P0005", "Pet Food", 40000, "https://picsum.photos/240", 15, 0));
        productList.add(new productModel("PRD042", "Makanan Anjing", "P0005", "Pet Food", 50000, "https://picsum.photos/241", 15, 0));
        productList.add(new productModel("PRD043", "Snack Kucing", "P0005", "Pet Food", 25000, "https://picsum.photos/242", 15, 0));
        productList.add(new productModel("PRD044", "Snack Anjing", "P0005", "Pet Food", 30000, "https://picsum.photos/243", 15, 0));
        productList.add(new productModel("PRD045", "Vitamin Kucing", "P0005", "Pet Food", 20000, "https://picsum.photos/244", 15, 0));
        productList.add(new productModel("PRD046", "Vitamin Anjing", "P0005", "Pet Food", 22000, "https://picsum.photos/245", 15, 0));
        productList.add(new productModel("PRD047", "Pasir Kucing", "P0005", "Pet Food", 35000, "https://picsum.photos/246", 15, 0));
        productList.add(new productModel("PRD048", "Tempat Makan Hewan", "P0005", "Pet Food", 15000, "https://picsum.photos/247", 15, 0));
        productList.add(new productModel("PRD049", "Mainan Kucing", "P0005", "Pet Food", 18000, "https://picsum.photos/248", 15, 0));
        productList.add(new productModel("PRD050", "Mainan Anjing", "P0005", "Pet Food", 20000, "https://picsum.photos/249", 15, 0));

// ================= Personal Care (P0006) =================
        productList.add(new productModel("PRD051", "Sampo", "P0006", "Personal Care", 20000, "https://picsum.photos/250", 15, 0));
        productList.add(new productModel("PRD052", "Sabun Mandi", "P0006", "Personal Care", 15000, "https://picsum.photos/251", 15, 0));
        productList.add(new productModel("PRD053", "Pasta Gigi", "P0006", "Personal Care", 12000, "https://picsum.photos/252", 15, 0));
        productList.add(new productModel("PRD054", "Deodoran", "P0006", "Personal Care", 18000, "https://picsum.photos/253", 15, 0));
        productList.add(new productModel("PRD055", "Lotion", "P0006", "Personal Care", 25000, "https://picsum.photos/254", 15, 0));
        productList.add(new productModel("PRD056", "Pewangi Pakaian", "P0006", "Personal Care", 22000, "https://picsum.photos/255", 15, 0));
        productList.add(new productModel("PRD057", "Hand Sanitizer", "P0006", "Personal Care", 10000, "https://picsum.photos/256", 15, 0));
        productList.add(new productModel("PRD058", "Masker Wajah", "P0006", "Personal Care", 30000, "https://picsum.photos/257", 15, 0));
        productList.add(new productModel("PRD059", "Shaving Cream", "P0006", "Personal Care", 18000, "https://picsum.photos/258", 15, 0));
        productList.add(new productModel("PRD060", "Sikat Gigi", "P0006", "Personal Care", 15000, "https://picsum.photos/259", 15, 0));

// ================= Makanan (P0007) =================
        productList.add(new productModel("PRD061", "Roti Bakar", "P0007", "Makanan", 12000, "https://picsum.photos/260", 15, 0));
        productList.add(new productModel("PRD062", "Nasi Goreng", "P0007", "Makanan", 20000, "https://picsum.photos/261", 15, 0));
        productList.add(new productModel("PRD063", "Mie Goreng", "P0007", "Makanan", 15000, "https://picsum.photos/262", 15, 0));
        productList.add(new productModel("PRD064", "Burger", "P0007", "Makanan", 25000, "https://picsum.photos/263", 15, 0));
        productList.add(new productModel("PRD065", "Pizza Mini", "P0007", "Makanan", 30000, "https://picsum.photos/264", 15, 0));
        productList.add(new productModel("PRD066", "Sandwich", "P0007", "Makanan", 18000, "https://picsum.photos/265", 15, 0));
        productList.add(new productModel("PRD067", "Kue Lapis", "P0007", "Makanan", 12000, "https://picsum.photos/266", 15, 0));
        productList.add(new productModel("PRD068", "Martabak Manis", "P0007", "Makanan", 25000, "https://picsum.photos/267", 15, 0));
        productList.add(new productModel("PRD069", "Donat", "P0007", "Makanan", 10000, "https://picsum.photos/268", 15, 0));
        productList.add(new productModel("PRD070", "Bakso", "P0007", "Makanan", 20000, "https://picsum.photos/269", 15, 0));

// ================= Kebutuhan Dapur (P0008) =================
        productList.add(new productModel("PRD071", "Wajan", "P0008", "Kebutuhan Dapur", 50000, "https://picsum.photos/270", 15, 0));
        productList.add(new productModel("PRD072", "Panci", "P0008", "Kebutuhan Dapur", 55000, "https://picsum.photos/271", 15, 0));
        productList.add(new productModel("PRD073", "Talenan", "P0008", "Kebutuhan Dapur", 15000, "https://picsum.photos/272", 15, 0));
        productList.add(new productModel("PRD074", "Pisau Dapur", "P0008", "Kebutuhan Dapur", 20000, "https://picsum.photos/273", 15, 0));
        productList.add(new productModel("PRD075", "Sendok Garpu", "P0008", "Kebutuhan Dapur", 10000, "https://picsum.photos/274", 15, 0));
        productList.add(new productModel("PRD076", "Gelas", "P0008", "Kebutuhan Dapur", 8000, "https://picsum.photos/275", 15, 0));
        productList.add(new productModel("PRD077", "Piring", "P0008", "Kebutuhan Dapur", 12000, "https://picsum.photos/276", 15, 0));
        productList.add(new productModel("PRD078", "Teko", "P0008", "Kebutuhan Dapur", 15000, "https://picsum.photos/277", 15, 0));
        productList.add(new productModel("PRD079", "Loyang", "P0008", "Kebutuhan Dapur", 25000, "https://picsum.photos/278", 15, 0));
        productList.add(new productModel("PRD080", "Spatula", "P0008", "Kebutuhan Dapur", 8000, "https://picsum.photos/279", 15, 0));

// ================= Kesehatan (P0009) =================
        productList.add(new productModel("PRD081", "Obat Flu", "P0009", "Kesehatan", 15000, "https://picsum.photos/280", 15, 0));
        productList.add(new productModel("PRD082", "Vitamin C", "P0009", "Kesehatan", 20000, "https://picsum.photos/281", 15, 0));
        productList.add(new productModel("PRD083", "Plester Luka", "P0009", "Kesehatan", 5000, "https://picsum.photos/282", 15, 0));
        productList.add(new productModel("PRD084", "Masker Medis", "P0009", "Kesehatan", 10000, "https://picsum.photos/283", 15, 0));
        productList.add(new productModel("PRD085", "Termometer", "P0009", "Kesehatan", 30000, "https://picsum.photos/284", 15, 0));
        productList.add(new productModel("PRD086", "Hand Sanitizer", "P0009", "Kesehatan", 12000, "https://picsum.photos/285", 15, 0));
        productList.add(new productModel("PRD087", "Obat Batuk", "P0009", "Kesehatan", 15000, "https://picsum.photos/286", 15, 0));
        productList.add(new productModel("PRD088", "Salep Luka", "P0009", "Kesehatan", 10000, "https://picsum.photos/287", 15, 0));
        productList.add(new productModel("PRD089", "Kapsul Vitamin", "P0009", "Kesehatan", 25000, "https://picsum.photos/288", 15, 0));
        productList.add(new productModel("PRD090", "Alat Tensi", "P0009", "Kesehatan", 200000, "https://picsum.photos/289", 15, 0));

// ================= Life Style (P0010) =================
        productList.add(new productModel("PRD091", "Tas Ransel", "P0010", "Life Style", 150000, "https://picsum.photos/290", 15, 0));
        productList.add(new productModel("PRD092", "Kacamata Hitam", "P0010", "Life Style", 80000, "https://picsum.photos/291", 15, 0));
        productList.add(new productModel("PRD093", "Sepatu Sneakers", "P0010", "Life Style", 250000, "https://picsum.photos/292", 15, 0));
        productList.add(new productModel("PRD094", "Jam Tangan", "P0010", "Life Style", 500000, "https://picsum.photos/293", 15, 0));
        productList.add(new productModel("PRD095", "Topi", "P0010", "Life Style", 40000, "https://picsum.photos/294", 15, 0));
        productList.add(new productModel("PRD096", "Dompet", "P0010", "Life Style", 70000, "https://picsum.photos/295", 15, 0));
        productList.add(new productModel("PRD097", "Sabuk", "P0010", "Life Style", 50000, "https://picsum.photos/296", 15, 0));
        productList.add(new productModel("PRD098", "Gelang", "P0010", "Life Style", 30000, "https://picsum.photos/297", 15, 0));
        productList.add(new productModel("PRD099", "Kalung", "P0010", "Life Style", 60000, "https://picsum.photos/298", 15, 0));
        productList.add(new productModel("PRD100", "Payung", "P0010", "Life Style", 25000, "https://picsum.photos/299", 15, 0));

        return productList;
    }
    // Method untuk ambil produk berdasarkan kategoriId
    public static List<productModel> getProductsByCategory(String categoryId) {
        List<productModel> filteredList = new ArrayList<>();
        for (productModel p : getProductDummyList()) {
            if (categoryId.equals(p.getKategoriId())) {
                filteredList.add(p);
            }
        }
        return filteredList;
    }


    // Method untuk membuat Map kategori → List produk
    public static Map<String, List<productModel>> getProductsByCategoryMap() {
        Map<String, List<productModel>> map = new HashMap<>();
        for (productModel p : getProductDummyList()) {
            String catId = p.getKategoriId();
            if (!map.containsKey(catId)) {
                map.put(catId, new ArrayList<>());
            }
            map.get(catId).add(p);
        }
        return map;
    }
    // Method untuk ambil produk berdasarkan ID
    public static productModel getProductById(String productId) {
        for (productModel p : getProductDummyList()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null; // kalau tidak ditemukan
    }

}
