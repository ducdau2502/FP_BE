package gethigh.fp_be.dto.response;

import java.time.LocalDate;

public class RevenueTime {
    private LocalDate start;

    private LocalDate end;

    public RevenueTime() {
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
