/*
 * Aadhar UID Management.
 *
 * Copyright (C) 2012 Deepak Shakya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ignou.aadhar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.dao.CertificateDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Certificate;
import com.ignou.aadhar.service.CertificateService;

/**
 * Core implementation of CertificateService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    /**
     * Data Access Object for certificate table.
     */
    @Autowired
    private CertificateDao certificateDao;

    public GenericDao<Certificate, Integer> getCertificateDao() {
        return certificateDao;
    }

    public void setCertificateDao(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Transactional
    public Certificate add(Certificate certificate) throws RuntimeException {
        return certificateDao.add(certificate);
    }

    @Transactional
    public Certificate modify(Certificate certificate) throws RuntimeException {
        return certificateDao.modify(certificate);
    }

    @Transactional
    public Certificate read(Integer id) throws RuntimeException {
        return certificateDao.read(id);
    }

    @Transactional
    public List<Certificate> list() throws RuntimeException {
        return certificateDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        certificateDao.delete(id);
    }
}
