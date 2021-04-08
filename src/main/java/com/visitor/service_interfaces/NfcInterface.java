package com.visitor.service_interfaces;

import com.visitor.entities.visitor.Nfc;

public interface NfcInterface extends BaseServiceInterface<Nfc>{
    public Nfc findByNfcId(String nfcRef);
}
