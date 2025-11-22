package com.sametkaya_finch.service.impl;

import java.util.Date;
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

}
