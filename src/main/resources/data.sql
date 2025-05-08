CREATE OR REPLACE VIEW vw_advanced_project_dashboard AS
SELECT
    p.id AS project_id,
    COUNT(e.id) AS total_entregaveis,
    COUNT(CASE WHEN e.data_entrega <= e.data_prevista_entrega THEN 1 END) AS entregues_no_prazo,
    COUNT(CASE WHEN e.data_entrega > e.data_prevista_entrega THEN 1 END) AS entregues_com_atraso,
    COUNT(CASE WHEN e.data_prevista_entrega < CURRENT_DATE AND e.data_entrega IS NULL THEN 1 END) AS em_atraso,
    ROUND(
        COUNT(CASE WHEN e.data_entrega <= e.data_prevista_entrega THEN 1 END) * 100.0 / NULLIF(COUNT(e.id), 0),
        2
    ) AS indice_desempenho_prazo,
    ROUND(AVG(e.data_entrega - e.data_inicio)::numeric, 2) AS tempo_medio_entrega,
    ROUND(STDDEV(e.data_entrega - e.data_inicio)::numeric, 2) AS desvio_padrao_tempo_entrega,
    COUNT(CASE WHEN e.status = 'ENTREGUE' THEN 1 END) * 100.0 / NULLIF(COUNT(e.id), 0) AS taxa_conclusao_entregaveis,
    COUNT(CASE WHEN e.status = 'PENDENTE' THEN 1 END) AS entregaveis_em_andamento,
    COALESCE(uc.count, 0) AS upvote_count,
    (SELECT COUNT(*) FROM tb_upvote WHERE projeto_id = p.id AND data_criacao >= CURRENT_DATE - INTERVAL '30 days') AS upvotes_ultimo_mes,
    ROUND(
        (SELECT COUNT(*) FROM tb_upvote WHERE projeto_id = p.id AND data_criacao >= CURRENT_DATE - INTERVAL '30 days') * 100.0 / NULLIF(COALESCE(uc.count, 0), 0),
        2
    ) AS taxa_crescimento_upvotes,
    COUNT(CASE WHEN EXTRACT(MONTH FROM e.data_entrega) = EXTRACT(MONTH FROM CURRENT_DATE) THEN 1 END) AS entregaveis_este_mes
FROM
    tb_projeto p
LEFT JOIN
    tb_entregavel e ON e.project_id = p.id
LEFT JOIN
    tb_upvote_count uc ON uc.projeto_id = p.id
LEFT JOIN
    tb_upvote u ON u.projeto_id = p.id
GROUP BY
    p.id, p.nome, p.data_cadastro, p.status, uc.count;