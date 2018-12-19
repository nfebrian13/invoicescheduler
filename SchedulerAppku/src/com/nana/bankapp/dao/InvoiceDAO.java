package com.nana.bankapp.dao;

import java.util.List;

import com.nana.bankapp.model.Invoice;

public interface InvoiceDAO {

	public boolean saveInvoice(Invoice invoice);
	public boolean editInvoice(Invoice invoice);
	public List<Invoice> getInvoices();
	public Invoice getInvoice(String invoiceId);
	public boolean deleteInvoice(String invoiceId);

}