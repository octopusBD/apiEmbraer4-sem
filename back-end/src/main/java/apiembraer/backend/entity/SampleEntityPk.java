package apiembraer.backend.entity;

import java.io.Serializable;

public class SampleEntityPk implements Serializable {
    private ChassiEntity idChassi;
    private BoletimEntity idBoletim;


    public SampleEntityPk(ChassiEntity idChassi, BoletimEntity idBoletim) {
        this.idChassi = idChassi;
        this.idBoletim = idBoletim;
    }


    public ChassiEntity getIdChassi() {
        return idChassi;
    }
    public void setIdChassi(ChassiEntity idChassi) {
        this.idChassi = idChassi;
    }

    public BoletimEntity getIdBoletim() {
        return idBoletim;
    }
    public void setIdBoletim(BoletimEntity idBoletim) {
        this.idBoletim = idBoletim;
    }
}
