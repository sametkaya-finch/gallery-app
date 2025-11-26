package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;
import com.sametkaya_finch.entity.Account;
import com.sametkaya_finch.entity.Address;
import com.sametkaya_finch.entity.Customer;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.AccountRepository;
import com.sametkaya_finch.repository.AddressRepository;
import com.sametkaya_finch.repository.CustomerRepository;
import com.sametkaya_finch.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

		// requestten gelen adres idye gore o adres var mi, yoksa hata
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}

		// requestten gelen account idye gore o account var mi, yoksa hata
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if (optAccount.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));

		}

		// varsa
		Customer customer = new Customer();
		customer.setCreateTimeDate(new Date());
		BeanUtils.copyProperties(dtoCustomerIU, customer);

		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());

		return customer;
	}

	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

		DtoCustomer dtoCustomer = new DtoCustomer();
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);

		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();

		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);

		return dtoCustomer;
	}

	@Override
	public DtoCustomer getCustomerById(Long id) {
		Optional<Customer> optId = customerRepository.findById(id);
		if (optId.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}

		Customer customer = optId.get();
		DtoCustomer dtoCustomer = new DtoCustomer();
		BeanUtils.copyProperties(customer, dtoCustomer);

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
		dtoCustomer.setAddress(dtoAddress);

		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
		dtoCustomer.setAccount(dtoAccount);

		return dtoCustomer;

	}

	@Override
	public List<DtoCustomer> getAllCustomers() {

		List<Customer> customers = customerRepository.findAll();
		List<DtoCustomer> dtoCustomersList = new ArrayList<>();

		for (Customer customer : customers) {

			DtoCustomer dtoCustomer = new DtoCustomer();
			BeanUtils.copyProperties(customer, dtoCustomer);

			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
			dtoCustomer.setAddress(dtoAddress);

			DtoAccount dtoAccount = new DtoAccount();
			BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
			dtoCustomer.setAccount(dtoAccount);

			dtoCustomersList.add(dtoCustomer);

		}

		return dtoCustomersList;
	}

	@Override
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {

		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if (optAddress.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));

		}

		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if (optAccount.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));

		}

		Customer customer = optCustomer.get();
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());

		Customer updatedCustomer = customerRepository.save(customer);

		DtoCustomer dtoCustomer = new DtoCustomer();
		BeanUtils.copyProperties(updatedCustomer, dtoCustomer);

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(updatedCustomer.getAddress(), dtoAddress);
		dtoCustomer.setAddress(dtoAddress);

		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(updatedCustomer.getAccount(), dtoAccount);
		dtoCustomer.setAccount(dtoAccount);

		return dtoCustomer;
	}

	@Override
	public void deleteCustomer(Long id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Customer customer = optCustomer.get();

		customerRepository.delete(customer);

	}

}
