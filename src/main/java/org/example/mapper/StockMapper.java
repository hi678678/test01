package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.domain.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper extends BaseMapper<Stock> {
}
