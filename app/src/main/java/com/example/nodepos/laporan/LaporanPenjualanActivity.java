package com.example.nodepos.laporan;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.adapter.LaporanAdapter;
import com.example.nodepos.model.riwayatModel;
import com.example.nodepos.repository.historyListRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LaporanPenjualanActivity extends AppCompatActivity {

    private List<riwayatModel> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_penjualan);

        RecyclerView rvLaporan = findViewById(R.id.rvLaporan);
        TextView tvTotalSummary = findViewById(R.id.tvTotalSummary);
        Button btnPdf = findViewById(R.id.btnPdf);
        Button btnCsv = findViewById(R.id.btnCsv);

        list = historyListRepository.getRiwayatList();

        // hitung total delivered
        int totalHarga = 0;
        int totalPesanan = 0;
        for (riwayatModel item : list) {
            if ("Delivered".equalsIgnoreCase(item.getStatus())) {
                totalHarga += Integer.parseInt(item.getTotalAmount());
                totalPesanan++;
            }
        }
        tvTotalSummary.setText("Total : Rp " + totalHarga + " (" + totalPesanan + " Pesanan)");

        rvLaporan.setLayoutManager(new LinearLayoutManager(this));
        rvLaporan.setAdapter(new LaporanAdapter(list));

        btnPdf.setOnClickListener(v -> generatePdf(this));
//        btnExcel.setOnClickListener(v -> generateExcel(this));
    }

    private void generatePdf(Context ctx) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(new Date());
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                    "LaporanPenjualan_" + timeStamp + ".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.add(new Paragraph("Laporan Penjualan"));
            document.add(new Paragraph("Tanggal: " + new Date().toString()));
            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(4);
            table.addCell(new PdfPCell(new Paragraph("OrderID")));
            table.addCell(new PdfPCell(new Paragraph("Status")));
            table.addCell(new PdfPCell(new Paragraph("Total")));
            table.addCell(new PdfPCell(new Paragraph("Tanggal")));

            for (riwayatModel item : list) {
                table.addCell(item.getOrderId());
                table.addCell(item.getStatus());
                table.addCell(item.getTotalAmount());
                table.addCell(item.getDate());
            }

            document.add(table);
            document.close();

            Toast.makeText(ctx, "PDF tersimpan di: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ctx, "Gagal generate PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

//    private void generateExcel(Context ctx) {
//        try {
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(new Date());
//            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
//                    "LaporanPenjualan_" + timeStamp + ".xlsx");
//
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Penjualan");
//
//            Row header = sheet.createRow(0);
//            header.createCell(0).setCellValue("OrderID");
//            header.createCell(1).setCellValue("Status");
//            header.createCell(2).setCellValue("Total");
//            header.createCell(3).setCellValue("Tanggal");
//
//            int rowIndex = 1;
//            for (riwayatModel item : list) {
//                Row row = sheet.createRow(rowIndex++);
//                row.createCell(0).setCellValue(item.getOrderId());
//                row.createCell(1).setCellValue(item.getStatus());
//                row.createCell(2).setCellValue(item.getTotalAmount());
//                row.createCell(3).setCellValue(item.getDate());
//            }
//
//            FileOutputStream fos = new FileOutputStream(file);
//            workbook.write(fos);
//            fos.close();
//            workbook.close();
//
//            Toast.makeText(ctx, "Excel tersimpan di: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            Toast.makeText(ctx, "Gagal generate Excel: " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
}
