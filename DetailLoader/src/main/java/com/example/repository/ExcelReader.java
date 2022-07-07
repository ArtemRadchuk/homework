package com.example.repository;

import com.example.model.XlsxModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.impl.ArticleValidatorImpl;
import service.impl.SeriesValidatorImpl;
import service.impl.TypeValidatorImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    @Getter
    @Setter
    public boolean findErrors;

    public List<XlsxModel> reade(String fileLocation) {
        List<XlsxModel> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(fileLocation));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    XlsxModel model = new XlsxModel();
                    for (Cell cell : row) {
                        DataFormatter formatter = new DataFormatter();
                        String str;
                        int intNum;
                        double dNum;
                        switch (cell.getAddress().getColumn()) {
                            case 0:
                                model.setSeries(cell.getStringCellValue());
                                SeriesValidatorImpl validator = new SeriesValidatorImpl();
                                if (validator.seriesComment(cell.getStringCellValue()) != null) {
                                    model.setCommentSeries(validator.seriesComment(cell.getStringCellValue()));
                                    setFindErrors(false);
                                }
                                break;
                            case 1:
                                model.setType(cell.getStringCellValue());
                                TypeValidatorImpl typeValidator = new TypeValidatorImpl();
                                if (typeValidator.seriesComment(cell.getStringCellValue()) != null) {
                                    model.setCommentType(typeValidator.seriesComment(cell.getStringCellValue()));
                                    setFindErrors(false);
                                }
                                break;
                            case 2:
                                model.setArticle(cell.getStringCellValue());
                                ArticleValidatorImpl articleValidator = new ArticleValidatorImpl();
                                if (articleValidator.articleComment(cell.getStringCellValue()) != null) {
                                    model.setCommentArticle(articleValidator.articleComment(cell.getStringCellValue()));
                                    setFindErrors(false);
                                }
                                break;
                            case 3:
                                model.setName(cell.getStringCellValue());
                                if (model.getName().isEmpty()) {
                                    model.setCommentName("Поле \"Наименование\" не заполнено");
                                    setFindErrors(false);
                                }
                                break;
                            case 4:
                                model.setExecution(cell.getStringCellValue());
                                break;
                            case 5:
                                str = formatter.formatCellValue(cell);
                                intNum = Integer.parseInt(str);
                                model.setLength(intNum);
                                break;
                            case 6:
                                model.setWidth((int) cell.getNumericCellValue());
                                break;
                            case 7:
                                model.setHeight((int) cell.getNumericCellValue());
                                break;
                            case 8:
                                str = formatter.formatCellValue(cell);
                                str = str.replaceAll(",", ".");
                                dNum = Double.parseDouble(str);
                                model.setThickness(dNum);
                                break;
                            case 9:
                                str = formatter.formatCellValue(cell);
                                str = str.replaceAll(",", ".");
                                dNum = Double.parseDouble(str);
                                model.setWeight(dNum);
                                if (str.isEmpty() && model.getWeight() == 0.0) {
                                    model.setCommentWeight("Поле масса не заполнено");
                                    setFindErrors(false);
                                }
                                break;
                            case 10:
                                str = formatter.formatCellValue(cell);
                                str = str.replaceAll(",", ".");
                                dNum = Double.parseDouble(str);
                                model.setPerforation(dNum);
                                break;
                            case 11:
                                str = formatter.formatCellValue(cell);
                                intNum = Integer.parseInt(str);
                                model.setStep(intNum);
                                break;
                        }
                    }
                    list.add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}